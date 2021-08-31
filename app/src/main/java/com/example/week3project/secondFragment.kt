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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3project.model.UserData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malkinfo.editingrecyclerview.view.UserAdpter


class secondFragment : Fragment() {
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
        val addDialog = AlertDialog.Builder(requireContext())


       addDialog.setView(v)
        addDialog.setPositiveButton("ok") { dialog, _ ->

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

            /*date picker and time picker not working inside dilog box even if code is correect*/

            date.setOnClickListener {

                val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    dateview.text = "" + mYear + "/" + mMonth + "/" + mDay
                },
                    year, month, day)
                dpd.show()
            }

            /*if else condtion is not working direct app closing*/


    //            if (names.isEmpty()) {
    //                Toast.makeText(context, "please enter name", Toast.LENGTH_SHORT).show()
    //                return@setOnClickListener
    //
    //            }
    //            if (emails.matches(emailPattern.toRegex())) {
    //            } else {
    //                Toast.makeText(context, "please enter valid Email", Toast.LENGTH_SHORT).show()
    //                return@setOnClickListener
    //            }

//            if (isSelected == -1) {
//                Toast.makeText(context, "please select gender", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (!Checkbtn.isChecked) {
//                Toast.makeText(context, "please check T&C", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }


    //            fun validUser(): Boolean {
    //                return if (!names.isEmpty()) {
    //                    if (names.length >= 5 && names.length <= 30) {
    //                        true
    //                    } else {
    //               Toast.makeText(context, "please enter correct name", Toast.LENGTH_SHORT).show()
    //                        false
    //                    }
    //                } else {
    //                    Toast.makeText(context, "please enter  name", Toast.LENGTH_SHORT).show()
    //                    true
    //                }
    //            }
    //
    //
    //            fun validEmail(): Boolean {
    //                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    //                return if (emails.isEmpty()) {
    //                    Toast.makeText(context, "please enter email", Toast.LENGTH_SHORT).show()
    //                    false
    //
    //                } else {
    //                    true
    //                }
    //            }
    //
    //
    //            fun validateGender(): Boolean {
    //
    //
    //                return if (isSelected == -1) {
    //                    Toast.makeText(context, "Please Select Gender", Toast.LENGTH_SHORT).show()
    //                    false
    //                } else {
    //
    //                    true
    //                }
    //            }
    //
    //            fun validateCheck(): Boolean {
    //                return if (!Checkbtn.isChecked) {
    //                    true
    //                } else {
    //                    Toast.makeText(context, "Please Select T&C", Toast.LENGTH_SHORT).show()
    //                    false
    //                }
    //            }
    //
    //
    //            if (!validUser() or !validEmail() or !validateGender() or !validateCheck()) {
    //      return@setPositiveButton
    //            }
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()


            userList.add(UserData("Name : $names", "Email : $emails","Gender : $orderString"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(activity, "adding user  Successsfull", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("cancel") { dilog, _ ->
            dilog.dismiss()
            Toast.makeText(activity, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
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