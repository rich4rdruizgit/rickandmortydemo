package com.example.rickandmortydemo

import com.example.rickandmortydemo.common.Environments

class RickMortyApplication {
    companion object{
        lateinit var environments : Environments
        fun init(environments: Environments) {
            this.environments = environments
        }
    }
}