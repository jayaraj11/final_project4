package com.example.week3project

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3project.model.UserData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malkinfo.editingrecyclerview.view.UserAdpter


class dataFragment : Fragment() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdpter
    private lateinit var Submitbtn : Button
    private lateinit var Groupbtn : RadioGroup
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
//    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addInfo() {
        val inflater = LayoutInflater.from(requireContext())

        val v = inflater.inflate(R.layout.fragment_data, null)

        val userName = v.findViewById<EditText>(R.id.idname)
        val userEmail = v.findViewById<EditText>(R.id.idemail)
        val Groupbtn = v.findViewById<RadioGroup>(R.id.groupbtn)
        val Checkbtn = v.findViewById<CheckBox>(R.id.checkbtn)


            val names = userName.text.toString()
            val emails = userEmail.text.toString()
            val isSelected = Groupbtn.checkedRadioButtonId
            val gender = v.findViewById<RadioButton>(isSelected)
            val orderString = gender.text.toString()

            val date = v.findViewById<Button>(R.id.datebtn)
            val dateview = v.findViewById<TextView>(R.id.dateview)

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            date.setOnClickListener {

                val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    dateview.text = "" + mYear + "/" + mMonth + "/" + mDay
                },
                    year, month, day)
                dpd.show()
            }
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()


            userList.add(UserData("Name : $names", "Email : $emails","Gender : $orderString"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(activity, "adding user  Successsfull", Toast.LENGTH_SHORT).show()

        val SecondFragment = secondFragment()
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.mainlayout, SecondFragment)
        transaction.commit()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val Mname = view.findViewById<TextView>(R.id.mTitle)
        val Memail  = view.findViewById<TextView>(R.id.mSubTitle)
        val Mgender = view.findViewById<TextView>(R.id.mgender)

        userList = ArrayList()

        addsBtn = view.findViewById(R.id.addingbtn)
        recv = view.findViewById(R.id.mRecycler)
        userAdapter = UserAdpter(context as MainActivity, userList)

        //setRecycler view adpter

        recv.layoutManager = LinearLayoutManager(requireContext())
        recv.adapter = userAdapter
        addsBtn.setOnClickListener { addInfo() }
        return view

    }

}