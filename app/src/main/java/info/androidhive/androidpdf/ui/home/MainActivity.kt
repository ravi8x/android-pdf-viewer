package info.androidhive.androidpdf.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.androidhive.androidpdf.databinding.ActivityMainBinding
import info.androidhive.androidpdf.ui.pdf.ViewPdfActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.content.btnSample1.setOnClickListener {
            openPdf(
                "Lorem ipsum ",
                "https://file-examples.com/storage/fe58a1f07d66f447a9512f1/2017/10/file-example_PDF_1MB.pdf"
            )
        }

        binding.content.btnSample2.setOnClickListener {
            openPdf(
                "Acrylic Laminates",
                "https://advancelam.com/wp-content/uploads/2024/08/ACRYLIC-Pdf.pdf"
            )
        }
    }

    private fun openPdf(title: String, url: String) {
        startActivity(Intent(this, ViewPdfActivity::class.java).apply {
            putExtra("title", title)
            putExtra("pdf_url", url)
        })
    }
}