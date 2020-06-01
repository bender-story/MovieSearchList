package com.rahul.network

import com.rahul.Constants
import com.rahul.ServiceType
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
/**
 * Service helper class that helps to create either service Interface
 * Mock service Interface
 *
 */
class ServiceAPIHelper(serviceType: ServiceType){
    private var serviceInterface: ServiceInterface?=null
    init {
        when(serviceType) {
            ServiceType.API->
                serviceInterface =
                    NetworkAPIController.getApiClient(Constants.NEWS_API_BASE_URL)?.create(
                        ServiceInterface::class.java)
            else-> serviceInterface =createMockServiceImpl()
        }
    }

    fun getServiceinterface(): ServiceInterface? {
        return serviceInterface
    }
    /**
     * returns a mock service interface object
     */
    private fun createMockServiceImpl(): ServiceInterface {
        val retrofit = NetworkAPIController.getApiClient(Constants.NEWS_API_BASE_URL)
        val behavior = NetworkBehavior.create()
        val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()
        val delegate: BehaviorDelegate<ServiceInterface> = mockRetrofit.create(ServiceInterface::class.java)
        return MockServiceImpl(delegate)
    }
}