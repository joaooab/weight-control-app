package com.br.weightcontrol.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.br.weightcontrol.R
import com.br.weightcontrol.data.user.User
import com.br.weightcontrol.extension.hideBottomNavigationView
import com.br.weightcontrol.extension.showBottomNavigationView
import com.br.weightcontrol.util.ValidatorBuilder
import kotlinx.android.synthetic.main.fragment_create_user.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserCreateFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModel()

    companion object {
        private const val MAX_HEIGHT = 300
        private const val MIN_HEIGHT = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigationView()
        setUpButtonSave()
    }

    private fun setUpButtonSave() {
        buttonSave.setOnClickListener {
            val succes = ValidatorBuilder()
                .isRequired(editTextLayoutName)
                .isRequired(editTextLayoutAge)
                .isRequired(editTextHeight)
                .isBetween(editTextHeight, MIN_HEIGHT, MAX_HEIGHT)
                .build()
            if (succes) {
                val user = createUser()
                userViewModel.save(user)
                Navigation.findNavController(view!!).popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showBottomNavigationView()
    }



    private fun createUser(): User {
        val name = editTextLayoutName.text.toString()
        val age = editTextLayoutAge.text.toString().toInt()
        val height = editTextHeight.text.toString().toInt()
        val gender = if (radioGroup.checkedRadioButtonId == R.id.radioButtonMale) {
            User.MALE
        } else {
            User.FEMALE
        }
        return User(name, age, gender, height)
    }

}