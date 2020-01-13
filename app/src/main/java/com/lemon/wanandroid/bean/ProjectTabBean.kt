package com.lemon.wanandroid.bean

data class ProjectTabBean(
    var children: List<Any>,
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var visible: Int
)