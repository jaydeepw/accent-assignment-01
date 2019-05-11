package com.github.jaydeepw.assignment01.models.datasource.mock

import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import java.util.*

class MainMockModel: MainContractInterface.Model {

    override fun getData(): List<String> {
        return Arrays.asList("stirng1","stirng2")
    }
}