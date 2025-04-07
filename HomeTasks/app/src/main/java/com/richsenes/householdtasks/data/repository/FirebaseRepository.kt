package com.richsenes.householdtasks.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.richsenes.householdtasks.data.model.House
import com.richsenes.householdtasks.data.model.Task
import com.richsenes.householdtasks.data.model.User
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getUser(userId: String): User? {
        val doc = db.collection("users").document(userId).get().await()
        return doc.toObject<User>()
    }

    suspend fun updateUser(user: User) {
        db.collection("users").document(user.id).set(user).await()
    }

    suspend fun getTasksForHouse(houseId: String): List<Task> {
        val snapshot = db.collection("tasks").whereEqualTo("houseId", houseId).get().await()
        return snapshot.documents.mapNotNull { it.toObject<Task>() }
    }

    suspend fun addTask(task: Task) {
        db.collection("tasks").document(task.id).set(task).await()
    }

    suspend fun completeTask(taskId: String, userId: String) {
        val taskRef = db.collection("tasks").document(taskId)
        val doc = taskRef.get().await()
        val task = doc.toObject<Task>() ?: return

        task.completedBy = userId
        taskRef.set(task).await()

        val creatorGetsHalf = userId == task.creatorId
        val pointsToAdd = if (creatorGetsHalf) task.importance / 2 else task.importance

        val user = getUser(userId)
        if (user != null) {
            val updatedUser = user.copy(points = user.points + pointsToAdd)
            updateUser(updatedUser)
        }
    }

    suspend fun getHouse(houseId: String): House? {
        val doc = db.collection("houses").document(houseId).get().await()
        return doc.toObject<House>()
    }

    suspend fun createHouse(house: House) {
        db.collection("houses").document(house.id).set(house).await()
    }

    suspend fun addUserToHouse(userId: String, houseId: String) {
        val house = getHouse(houseId)
        val user = getUser(userId)
        if (house != null && user != null) {
            val updatedHouse = house.copy(memberIds = house.memberIds + userId)
            val updatedUser = user.copy(houseIds = user.houseIds + houseId)
            db.collection("houses").document(houseId).set(updatedHouse).await()
            db.collection("users").document(userId).set(updatedUser).await()
        }
    }
}
