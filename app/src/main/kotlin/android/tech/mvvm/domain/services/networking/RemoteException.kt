package android.tech.mvvm.domain.services.networking

import retrofit2.Response

class RemoteException(val response: Response<*>) : Exception()
