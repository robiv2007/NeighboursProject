package com.example.neighbourproject.ui.edit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.neighbourproject.R
import com.example.neighbourproject.neighbour.data.Gender
import com.example.neighbourproject.neighbour.data.People
import com.example.neighbourproject.neighbour.data.RelationshipStatus

import com.example.neighbourproject.ui.search.SearchActivity

open class EditProfileActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "EditProfileActivity"
    }

    private val model: EditViewModel by viewModels()

    lateinit var checkBox: ImageView
    lateinit var checkBox3: ImageView
    lateinit var imageView: ImageView
    lateinit var nameEditText: EditText
    lateinit var lastnameEditText: EditText
    lateinit var ageEditText: EditText
    lateinit var interestsEditText: EditText
    lateinit var genderSpinner: Spinner
    lateinit var relationshipSpinner: Spinner
    lateinit var saveButton: Button
    lateinit var emailEditText: EditText
    var profile: People? = null

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        title = "NeighbourProject"

        checkBox = findViewById(R.id.checkBox)
        checkBox3 = findViewById(R.id.checkBox3)
        imageView = findViewById(R.id.imageView)
        nameEditText = findViewById(R.id.nameEditText)
        lastnameEditText = findViewById(R.id.lastnameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        interestsEditText = findViewById(R.id.interestsEditText)
        genderSpinner = findViewById(R.id.genderSpinner)
        relationshipSpinner = findViewById(R.id.relationshipSpinner)
        saveButton = findViewById(R.id.button)
        emailEditText = findViewById(R.id.emailEditText)

        val adapter = ArrayAdapter<Gender>(this, android.R.layout.simple_spinner_item, Gender.values())
        genderSpinner.adapter = adapter

        val adapter2 = ArrayAdapter<RelationshipStatus>(this, android.R.layout.simple_spinner_item, RelationshipStatus.values())
        relationshipSpinner.adapter = adapter2

        profile = model.getUserProfile()
        if (profile == null) {
            profile = People()
        } else {
            nameEditText.setText(profile?.firstName)
            lastnameEditText.setText(profile?.lastName)
            ageEditText.setText(profile?.age.toString())
            genderSpinner.setSelection(profile?.gender!!.ordinal)
            relationshipSpinner.setSelection(profile?.relationshipStatus!!.ordinal)
            emailEditText.setText(profile?.email)


        }


        saveButton.setOnClickListener {
            profile?.firstName = nameEditText.text.toString()
            profile?.lastName = lastnameEditText.text.toString()
            profile?.age = ageEditText.text.toString().toInt()
            profile?.gender = Gender.valueOf(genderSpinner.selectedItem.toString())
            profile?.relationshipStatus = RelationshipStatus.valueOf(relationshipSpinner.selectedItem.toString())
            profile?.interests
            profile?.email = emailEditText.text.toString()

            profile?.let {
                model.editUserProfile(it)
            }

            startActivity(Intent(this, SearchActivity::class.java))
        }

        nameEditText.doAfterTextChanged {
            if (nameEditText.length() > 3) {
                checkBox.visibility = View.VISIBLE
            } else {
                checkBox.visibility = View.INVISIBLE
            }
        }

        interestsEditText.doAfterTextChanged {
            if (interestsEditText.length() > 1) {
                checkBox3.visibility = View.VISIBLE
            } else {
                checkBox3.visibility = View.INVISIBLE
            }
        }

        imageView.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)

        }


    }


}








