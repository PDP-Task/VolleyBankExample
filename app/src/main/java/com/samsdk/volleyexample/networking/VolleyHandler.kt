package com.samsdk.volleyexample.networking

interface VolleyHandler {
    fun onSuccess(response: String?)
    fun onError(error: String?)
}