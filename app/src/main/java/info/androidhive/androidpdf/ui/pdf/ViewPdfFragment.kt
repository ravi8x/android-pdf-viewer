package info.androidhive.androidpdf.ui.pdf

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import info.androidhive.androidpdf.R
import info.androidhive.androidpdf.databinding.FragmentViewPdfBinding

class ViewPdfFragment : Fragment() {
    private val binding by lazy {
        FragmentViewPdfBinding.inflate(layoutInflater)
    }
    private val viewModel: ViewPdfViewModel by viewModels()
    private var title: String? = null
    private var pdfUrl: String? = null

    companion object {
        fun newInstance(bundle: Bundle?) = ViewPdfFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title")
            pdfUrl = it.getString("pdf_url")
        }
    }

    private fun bindObservers() {
        viewModel.fileStream.observe(this) { response ->
            if (response?.isSuccessful == true) {
                binding.apply {
                    binding.pdfViewer.fromStream(response.body()?.byteStream())
                        .onLoad {
                            progressBar.hide()
                        }
                        .onError {
                            progressBar.hide()
                        }
                        .load()
                }
            } else {
                binding.progressBar.hide()
                Toast.makeText(activity, R.string.error_preview_pdf_file, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()

        // toolbar title
        activity?.title = title

        // fetch pdf from remote url
        viewModel.getFile(pdfUrl)
    }
}