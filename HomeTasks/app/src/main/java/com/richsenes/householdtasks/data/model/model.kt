// Modelli dati
package com.richsenes.householdtasks.data.model

data class User(
    val id: String = "",
    val name: String = "",
    val houseIds: List<String> = emptyList(),
    val points: Int = 0
)

data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val importance: Int = 1,
    val creatorId: String = "",
    val houseId: String = "",
    var completedBy: String? = null
)

data class House(
    val id: String = "",
    val name: String = "",
    val memberIds: List<String> = emptyList()
)