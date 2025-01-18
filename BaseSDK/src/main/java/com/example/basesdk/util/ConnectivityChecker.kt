package com.example.basesdk.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.gtn.basesdk.util.enums.ConnectionState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ConnectivityChecker(
    context: Context,
) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Expose connectivity status as a Flow of ConnectionState
    val connectivityStatus: Flow<ConnectionState> =
        callbackFlow {
            // Define network callback to listen for connectivity changes
            val networkCallback =
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        // Check if the network is slow or fast
                        val networkSpeed = checkNetworkSpeed(network)
                        trySend(networkSpeed)
                        // Emit the detected speed state
                    }

                    override fun onLosing(
                        network: Network,
                        maxMsToLive: Int,
                    ) {
                        trySend(ConnectionState.DISCONNECTING)
                    }

                    private fun isNetworkStable(): Boolean {
                        val activeNetwork = connectivityManager.activeNetwork ?: return false
                        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    }

                    override fun onLost(network: Network) {
                        // Allow time for reconnection or transient fixes
                        Thread.sleep(500)
                        if (!isNetworkStable()) {
                            trySend(ConnectionState.DISCONNECTED)
                        }
                    }

                    override fun onUnavailable() {
                        trySend(ConnectionState.ERROR)
                    }
                }

            // Register network callback
            val networkRequest = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

            // Emit initial connectivity status
            trySend(getInitialConnectionState())

            // Close the flow when no longer needed
            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }

    // Helper function to determine initial network availability and speed
    private fun getInitialConnectionState(): ConnectionState {
        val activeNetwork = connectivityManager.activeNetwork ?: return ConnectionState.DISCONNECTED
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return ConnectionState.DISCONNECTED

        return when {
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED) -> {
                // For metered connections (like mobile data or hotspots), still count them as connected
                if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED) ||
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TEMPORARILY_NOT_METERED)
                ) {
                    ConnectionState.CONNECTED
                } else {
                    checkNetworkSpeed(activeNetwork)
                }
            }
            else -> ConnectionState.DISCONNECTED
        }
    }

    // Determine network speed based on network capabilities (rough check for demonstration)
    private fun checkNetworkSpeed(network: Network): ConnectionState {
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return ConnectionState.DISCONNECTED

        return when {
            // Check for validated internet connection
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) -> {
                // If validated, treat as connected
                ConnectionState.CONNECTED
            }

            // For unmetered (hotspot or mobile data) networks, assume they're usable
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED) -> {
                // For mobile hotspots or other valid, but unvalidated networks
                if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED) ||
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TEMPORARILY_NOT_METERED)
                ) {
                    ConnectionState.CONNECTED
                } else {
                    // Treat as connected but slow if bandwidth is low
                    if (capabilities.linkDownstreamBandwidthKbps < 250) {
                        ConnectionState.SLOW
                    } else {
                        ConnectionState.CONNECTED
                    }
                }
            }

            // Consider "slow" if bandwidth is under a certain threshold
            capabilities.linkDownstreamBandwidthKbps < 250 -> ConnectionState.SLOW

            // Default to disconnected if none of the conditions match
            else -> ConnectionState.DISCONNECTED
        }
    }
}
