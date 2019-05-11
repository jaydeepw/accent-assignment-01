package com.github.jaydeepw.assignment01.models.datasource.network

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import java.util.*

class MainNetworkModel: MainContractInterface.Model {

    override fun getData(): List<String> {
        // todo: network call here.
        return Arrays.asList("stirng1","stirng2")
    }
}