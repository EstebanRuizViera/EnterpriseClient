package com.example.enterpriseclient.bottomNavigationView.user


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.enterpriseclient.MainActivity
import com.example.enterpriseclient.R
import com.example.enterpriseclient.myDataBase.viewModel.UsersViewModel
import com.example.enterpriseclient.requestServer.RequestUser
import kotlinx.android.synthetic.main.fragmen_edit.*

/**
 * A simple [Fragment] subclass.
 */
class EditFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel

    companion object {
        fun newInstance(): EditFragment = EditFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragmen_edit, container, false)

        var editName = root.findViewById<TextView>(R.id.editName)
        var editEmail = root.findViewById<TextView>(R.id.editEmail)
        var editBtn = root.findViewById<Button>(R.id.editBtn)


        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        editBtn.setOnClickListener() {
            var newModification = 0
            if (editName.text.toString().equals("")) {
                editName.setText(usersViewModel.getUser(1)!!.get(0).name)
                newModification++
            }
            if (editEmail.text.toString().equals("")) {
                editEmail.setText(usersViewModel.getUser(1)!!.get(0).email)
                newModification++
            }
            if(newModification!=2) {
                RequestUser.updateUser(activity as MainActivity, usersViewModel, editName, editEmail,usersViewModel.getUser(1)!!)
            }
        }
        return root
    }
}

