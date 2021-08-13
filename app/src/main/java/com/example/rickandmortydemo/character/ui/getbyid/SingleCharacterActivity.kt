package com.example.rickandmortydemo.character.ui.getbyid

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.rickandmortydemo.character.ui.getbyid.di.DaggerSingleCharacterComponent
import com.example.rickandmortydemo.character.ui.getbyid.di.SingleCharacterModule
import com.example.rickandmortydemo.character.ui.getbyid.viewmodel.SingleCharacterViewModel
import com.example.rickandmortydemo.character.ui.getbyid.viewmodel.SingleCharacterViewModelFactory
import com.example.rickandmortydemo.databinding.ActivitySingleCharacterBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class SingleCharacterActivity : AppCompatActivity() {

    @Inject
    lateinit var _viewModelSingleCharacterFactory: SingleCharacterViewModelFactory

    private lateinit var _viewModelSingleCharacter:SingleCharacterViewModel

    private lateinit var _binder : ActivitySingleCharacterBinding

    private var _loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSingleCharacterComponent.builder().singleCharacterModule(SingleCharacterModule(this))
            .build().inject(this)

        _binder = ActivitySingleCharacterBinding.inflate(layoutInflater)
        setContentView(_binder.root)

        _viewModelSingleCharacter = ViewModelProvider(this, _viewModelSingleCharacterFactory).get()

        setActionView()
        updateUi()
    }

    private fun setActionView() {
        val id = intent.getStringExtra("CHARACTER_ID")
        intent.getStringExtra("CHARACTER_ID")?.let {
            _viewModelSingleCharacter.getCharacterById(it.toInt())
        }
    }

    private fun updateUi() {
        _viewModelSingleCharacter.model.observe(this){
            when(it){
                is SingleCharacterViewModel.UiModel.Loading-> if (it.visible) showLoading() else hideLoading()
                is SingleCharacterViewModel.UiModel.SetCharacterData -> {
                    Picasso.get().load(it.info.image).into(_binder.imgSingleCharacter)
                    _binder.tvSingleCharacter.text = it.info.name
                }

            }
        }
    }

    private fun showLoading() {
        hideLoading()
        //_loadingDialog = DialogBuilder.showMessageLoadingDialog(this, DataBuilderDialog(getString(R.string.loadingMessage), TypeDialog.LOADING,false))
        _loadingDialog?.show()
    }

    private fun hideLoading(){
        _loadingDialog?.cancel()
    }
}