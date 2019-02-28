package com.shakutara.demomvvm.entity

import com.google.gson.annotations.SerializedName

class RepoSearchResponse {
    @SerializedName("total_count")
    var totalCount: Int = -1
}