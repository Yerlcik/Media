package com.example.kotlinsocialmedia


class StoriesModel {
    var storiesName: String? = null
    var storiesPic = 0

    constructor() {}
    constructor(storiesName: String?, storiesPic: Int) {
        this.storiesName = storiesName
        this.storiesPic = storiesPic
    }

}
