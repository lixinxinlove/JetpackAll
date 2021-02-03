package com.lixinxinlove.all.entity

import javax.inject.Inject

data class User constructor(var id: Int, var name: String, var mood: String) {

    @Inject
    constructor() : this(1, "lixinxin", "开心")
}