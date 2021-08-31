package com.malkinfo.editingrecyclerview.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.week3project.R
import com.example.week3project.model.UserData
import com.example.week3project.secondFragment
import com.example.week3project.thirdFragment

class UserAdpter(val c:Context,val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdpter.UserViewHolder>()
{

    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v){
        var name:TextView
        var email:TextView
        var gender:TextView
        var profilebtn:Button

        init {
            name = v.findViewById<TextView>(R.id.mTitle)
            email = v.findViewById<TextView>(R.id.mSubTitle)
            gender = v.findViewById<TextView>(R.id.mgender)
            profilebtn = v.findViewById(R.id.profilebtn)
//            profilebtn.setOnClickListener { popupMenus(it) }
        }

//        @SuppressLint("ResourceType")
//        private fun popupMenus(v:View) {
//            val position = userList[adapterPosition]
//            val popupMenus = PopupMenu(c,v)
//            popupMenus.inflate(R.id.profilebtn)
//            popupMenus.setOnMenuItemClickListener {
//
//
//                val thirdFragment = thirdFragment()
//                val transaction: FragmentTransaction = .beginTransaction()
//                transaction.replace(R.id.mainlayout, thirdFragment)
//                transaction.commit()
//            }
//        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v  = inflater.inflate(R.layout.item_list,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.email.text = newList.userEmail
        holder.gender.text = newList.userGender
    }

    override fun getItemCount(): Int {
        return  userList.size
    }
}