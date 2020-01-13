package com.lemon.wanandroid.bean

data class SystemMenuBean(
    var children: List<Children>,
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var visible: Int
) {
    data class Children(
        var children: List<Any>,
        var courseId: Int,
        var id: Int,
        var name: String,
        var order: Int,
        var parentChapterId: Int,
        var visible: Int
    )
}

