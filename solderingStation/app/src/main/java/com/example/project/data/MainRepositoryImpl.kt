package com.example.project.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.domain.AuthRepository
import com.example.project.domain.MainRepository
import com.example.project.domain.NotifyItem
import com.example.project.domain.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


//@BoundTo(supertype = MainRepository::class, component = SingletonComponent::class)
class MainRepositoryImpl @Inject constructor(): MainRepository {
    private val database  = Firebase.database
    val usersRef = database.getReference("users")
    val notificationRef = database.getReference("notification")
    val buttonRef = database.getReference("button")

//    private var studentsFromDb: MutableList<User> = mutableListOf()
    private var studentsData= MutableLiveData<List<User>>()
    private var notificationData= MutableLiveData<List<NotifyItem>>()

    override fun getStudents() : LiveData<List<User>>{
        var user: User
        val menuListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val studentsFromDb: MutableList<User> = mutableListOf()
                dataSnapshot.children.forEach{
                    user = it.getValue(User::class.java)!!

                    if (user.type == "Student"){
                        studentsFromDb.add(user)
                    }

                }

                studentsData.value=studentsFromDb


            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        usersRef.addValueEventListener(menuListener)


        return studentsData
    }

    override fun addNotification(notification: NotifyItem){

        notificationRef.push().setValue(notification)
    }

    override fun getNotifications(): LiveData<List<NotifyItem>>{
        var notifyItem: NotifyItem
        val menuListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val notificationFromDb: MutableList<NotifyItem> = mutableListOf()
                dataSnapshot.children.forEach{
                    notifyItem = it.getValue(NotifyItem::class.java)!!

                    notificationFromDb.add(notifyItem)


                }

                notificationData.value=notificationFromDb


            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        notificationRef.addValueEventListener(menuListener)


        return notificationData
    }

}