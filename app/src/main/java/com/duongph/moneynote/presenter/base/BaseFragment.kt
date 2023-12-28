package com.example.mynotehilt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.viewbinding.ViewBinding
import com.duongph.moneynote.BindingReflex
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

//    private val loadingDialog: LoadingProgressDialog by lazy {
//        LoadingProgressDialog(context)
//    }

    @Inject
    protected lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = BindingReflex.reflexViewBinding(javaClass, inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        initListener()
        initView()
        getViewModel().uiState.asLiveData().observe {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    abstract fun initObserve()
    abstract fun initView()
    abstract fun initListener()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun getViewModel(): BaseViewModel

    fun <T> LiveData<T>.observe(observer: Observer<T>) {
        this.observe(viewLifecycleOwner, observer)
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
//        loadingDialog.show()
    }

    fun hideLoading() {
//        loadingDialog.dismiss()
    }

}