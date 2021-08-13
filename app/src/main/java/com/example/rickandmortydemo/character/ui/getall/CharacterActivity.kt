package com.example.rickandmortydemo.character.ui.getall

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortydemo.character.ui.getbyid.SingleCharacterActivity
import com.example.rickandmortydemo.character.ui.getall.adapter.CharacterCard
import com.example.rickandmortydemo.character.ui.getall.adapter.OnCardClickListener
import com.example.rickandmortydemo.character.ui.getall.adapter.RecyclerCharacterAdapter
import com.example.rickandmortydemo.character.ui.getall.di.CharacterModule
import com.example.rickandmortydemo.character.ui.getall.di.DaggerCharacterComponent
import com.example.rickandmortydemo.character.ui.getall.viewmodel.CharacterViewModel
import com.example.rickandmortydemo.character.ui.getall.viewmodel.CharacterViewModelFactory
import com.example.rickandmortydemo.databinding.ActivityCharacterBinding
import javax.inject.Inject

class CharacterActivity : AppCompatActivity(), OnCardClickListener {

    @Inject
    lateinit var _viewModelCharacterFactory: CharacterViewModelFactory

    private lateinit var _viewModelCharacter: CharacterViewModel

    private lateinit var _binder: ActivityCharacterBinding

    private var _loadingDialog: Dialog? = null

    private lateinit var _listener:OnCardClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCharacterComponent.builder().characterModule(CharacterModule(this))
            .build().inject(this)


        _binder = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(_binder.root)

        _viewModelCharacter = ViewModelProvider(this, _viewModelCharacterFactory).get()

        _listener = this

        updateUi()
        setViewAction()

    }

    private fun setViewAction(){
        _viewModelCharacter.getAllCharacters()
    }

    private fun updateUi(){
        _viewModelCharacter.model.observe(this){
            when(it){
                is CharacterViewModel.UiModel.Loading -> if(it.visible) showLoading() else hideLoading()
                is CharacterViewModel.UiModel.CharacterRecyclerCard -> {
                    _binder.rvAllCharacters.apply {
                        layoutManager = GridLayoutManager(context, 2)

                        adapter = RecyclerCharacterAdapter(it.list,_listener)

                    }
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

    override fun onClick(characterCard: CharacterCard) {
        val intent = Intent(this@CharacterActivity, SingleCharacterActivity::class.java)
        intent.putExtra("CHARACTER_ID", characterCard.id.toString())
        startActivity(intent)
    }
}