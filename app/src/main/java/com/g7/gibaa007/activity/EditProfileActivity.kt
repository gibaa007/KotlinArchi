package com.g7.gibaa007.activity

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.design.widget.TextInputLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import com.g7.gibaa007.R
import com.g7.gibaa007.pojo.ProfilePojo
import com.g7.gibaa007.utils.*
import com.g7.gibaa007.utils.AppConfig.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.io.File

class EditProfileActivity : BaseActivity() {

   var commonAction: CommonActions? = null
   var profilePojo: ProfilePojo? = null
   var mFileprofileImage: File? = null
   var isCameraSelected = false
   var mImageCaptureUri: Uri? = null
   val PLACE_AUTOCOMPLETE_REQUEST_CODE = 7


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonAction = CommonActions(this)
        setContentView( R.layout.activity_edit_profile)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = "Profile Settings"
        iv_edit_pic.setOnClickListener { pickPhotoDialog(REQUEST_CODE_CROP_IMAGE) }
        bt_next.setOnClickListener { updateProfile() }
//        profilePojo = SingletonHolder.getInstance().getProfilePojo()
//        binding!!.setUser(profilePojo)
//        binding!!.etLocation.setOnClickListener(View.OnClickListener {
//            try {
//                val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this@EditProfileActivity)
//                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
//            } catch (e: GooglePlayServicesRepairableException) {
//                // TODO: Handle the error.
//            } catch (e: GooglePlayServicesNotAvailableException) {
//                // TODO: Handle the error.
//            }
//        })
    }


    private fun pickPhotoDialog(requestCodeCropImage: Int) {
        mFileprofileImage = null

        val state = Environment.getExternalStorageState()

        TEMP_PROFILE_PHOTO_FILE_NAME = (System.currentTimeMillis()).toString() + ".jpg"
        if (Environment.MEDIA_MOUNTED == state) {
            mFileprofileImage = File(Environment.getExternalStorageDirectory(),
                    TEMP_PROFILE_PHOTO_FILE_NAME)


        } else {

            mFileprofileImage = File(this.filesDir,
                    TEMP_PROFILE_PHOTO_FILE_NAME)
        }
        val item = arrayOf("Take Picture", "Upload From Gallery")
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Upload Image").setItems(item
        ) { dialog, which ->
            when (which) {
                0 -> {
                    isCameraSelected = true
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(this@EditProfileActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), AppConfig.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
                        } else if ((checkSelfPermission(Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(this@EditProfileActivity, arrayOf(Manifest.permission.CAMERA), AppConfig.MY_PERMISSIONS_REQUEST_CAMERA)
                        } else {
                            takePicture()
                        }
                    } else
                        takePicture()
                }
                1 -> {
                    isCameraSelected = false
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(this@EditProfileActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), AppConfig.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
                        } else {
                            openGallery()
                        }
                    } else {
                        openGallery()
                    }
                }
                else -> {
                }
            }
        }
        builder.show()

    }


    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            mImageCaptureUri = null
            val state = Environment.getExternalStorageState()
            if (Environment.MEDIA_MOUNTED == state) {
                mImageCaptureUri = FileProvider.getUriForFile(this, "com.g7.gibaa007.provider", mFileprofileImage!!)
            } else {
                mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri)
            intent.putExtra("return-data", true)
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE)
        } catch (e: ActivityNotFoundException) {
            Print.e(e.message)
        }


    }

    private fun openGallery() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY)
    }


//    /**
//     * Method to copy the input stream
//     *
//     * @param input
//     * @param output
//     * @throws IOException
//     */
//    @Throws(IOException::class)
//    fun copyStream(input: InputStream, output: OutputStream) {
//        val buffer = ByteArray(1024)
//        val bytesRead: Int
//        while ((bytesRead = input.read(buffer)) != -1) {
//            output.write(buffer, 0, bytesRead)
//        }
//    }

//    protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//
//        when (requestCode) {
//            REQUEST_CODE_GALLERY -> try {
//                val inputStream = contentResolver.openInputStream(data.data)
//                val fileOutputStream = FileOutputStream(mFileprofileImage!!)
//                copyStream(inputStream, fileOutputStream)
//                fileOutputStream.close()
//                inputStream.close()
//                startCropImage(data.data)
//            } catch (e: Exception) {
//                Log.e("error", "Error while creating temp file", e)
//            }
//
//            REQUEST_CODE_TAKE_PICTURE -> startCropImage(Uri.fromFile(mFileprofileImage))
//
//
//            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
//                val result = CropImage.getActivityResult(data)
//                if (resultCode == RESULT_OK) {
//                    val path = result.getUri().getPath() ?: return
//                    val imagePipeline = Fresco.getImagePipeline()
//                    binding!!.ivUserImage.setTag(AppConfig.NEW_PIC_ADDED)
//                    mFileprofileImage = File(path!!)
//                    imagePipeline.evictFromCache(result.getUri())
//                    binding!!.ivUserImage.post(object : Runnable {
//                        override fun run() {
//                            binding!!.ivUserImage.setImageURI(result.getUri())
//                            profilePojo!!.setImage("file://" + path!!)
//                            customProgressDialog!!.show("Uploading")
//                            beginUpload(mFileprofileImage)
//                        }
//                    })
//                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                    commonAction!!.showFailureSnackToast(binding!!.ivUserImage, "No Image Selected")
//                } else if (resultCode == RESULT_CANCELED) {
//                    commonAction!!.showFailureSnackToast(binding!!.ivUserImage, "Image Crop Cancelled")
//                }
//            }
//            PLACE_AUTOCOMPLETE_REQUEST_CODE -> if (resultCode == RESULT_OK) {
//
//                val place = PlaceAutocomplete.getPlace(this, data)
//                Log.i(AppConfig.TAG, "Place: " + place.getName())
//                binding!!.etLocation.setText(place.getAddress())
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                val status = PlaceAutocomplete.getStatus(this, data)
//                // TODO: Handle the error.
//                Log.i(AppConfig.TAG, status.getStatusMessage())
//
//            } else if (resultCode == RESULT_CANCELED) {
//                // The user canceled the operation.
//            }
//
//            else ->
//
//                callbackManagerFacebook!!.onActivityResult(requestCode, resultCode, data)
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }


//    /**
//     * Method to start crop image activity
//     *
//     * @param data
//     */
//    private fun startCropImage(data: Uri?) {
//        CropImage.activity(data)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setFixAspectRatio(true)
//                .setMinCropResultSize(200, 200)
//                .setMinCropWindowSize(200, 200)
//                .setAutoZoomEnabled(false)
//                .start(this@EditProfileActivity)
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            AppConfig.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE ->
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //                    checkCameraPermission();
                } else {
                    commonAction!!.customAlertDialog("Please allow denied Permission in Setting->app permissions", this)
                }
            AppConfig.MY_PERMISSIONS_REQUEST_CAMERA -> if ((grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(this@EditProfileActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), AppConfig.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
                } else {
                    takePicture()
                }
            } else {
                commonAction!!.customAlertDialog("Please allow denied Permission in Setting->app permissions", this)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        bindData()
        commonAction!!.hideSoftKeyboard(this@EditProfileActivity)
    }

    private fun bindData() {


//        binding!!.ivUserImage.setImageURI(profilePojo!!.getImage())
//        if (profilePojo!!.getVerified() || profilePojo!!.getVerifyMe()) {
//            binding!!.cbVerify.setKeyListener(null)
//
//        }
//        binding!!.cbVerify.setChecked(profilePojo!!.getVerifyMe())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Validating form
     */
    fun updateProfile() {
//        if (!validate(binding!!.etEmail)) {
//            return
//        }
//        if (!validate(binding!!.etNickname)) {
//            return
//        }
        //        if (!validate(binding.etLocation)) {
        //            return;
        //        }
//        profilePojo!!.setEmail(commonAction!!.getTextFrom(binding!!.etEmail))
//        profilePojo!!.setNickname(commonAction!!.getTextFrom(binding!!.etNickname))
//        profilePojo!!.setAddress(binding!!.etLocation.getText().toString())
//        profilePojo!!.setAboutMe(commonAction!!.getTextFrom(binding!!.etAbout))
//        profilePojo!!.setVerifyMe(if (binding!!.cbVerify.isChecked()) "Y" else "N")
//        customProgressDialog!!.show()
//        WebServices(this@EditProfileActivity).updateProfile(callBack, profilePojo)
    }

    private fun validate(v: EditText): Boolean {
        if (v.text.toString().trim { it <= ' ' }.isEmpty()) {
            val parent = v.parent.parent as TextInputLayout
            val hint = parent.hint!!.toString()
            v.error = "Enter $hint"
            requestFocus(v)
            return false
        } else {
            return true
        }
    }

    private fun requestFocus(view: View) {
        if (view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }


//    fun onTaskCompleted(response: CommonPojo<ProfilePojo>, taskType: Int, taskStatus: Boolean) {
//        if (customProgressDialog != null)
//            customProgressDialog!!.dismiss()
//        if (taskStatus) {
//            if (taskType == AppConfig.GET_PROFILES) {
//                SingletonHolder.getInstance().setProfilePojo(response.getData())
//                binding!!.ivUserImage.setImageURI(profilePojo!!.getImage())
//                binding!!.cbVerify.setChecked(profilePojo!!.getVerified())
//            } else if (taskType == AppConfig.UPDATE_PROFILE) {
//                SingletonHolder.getInstance().setProfilePojo(profilePojo)
//                commonAction!!.customAlertDialogFinish(response.getMessage(), this)
//            }
//        } else {
//            commonAction!!.customAlertDialog(response.getMessage(), this)
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish() // finish activity
    }

}
