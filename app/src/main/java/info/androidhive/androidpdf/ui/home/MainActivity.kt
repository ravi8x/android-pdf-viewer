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
                "https://firebasestorage.googleapis.com/v0/b/project-8525323942962534560.appspot.com/o/samples%2Fpdf%2Ffile-example_PDF_1MB.pdf?alt=media&token=ea88122f-0524-4022-b401-f8ec1035901f"
            )
        }

        binding.content.btnSample2.setOnClickListener {
            openPdf(
                "Acrylic Laminates",
                "https://firebasestorage.googleapis.com/v0/b/project-8525323942962534560.appspot.com/o/samples%2Fpdf%2FACRYLIC-Pdf.pdf?alt=media&token=84b69d38-10a3-41c4-8854-9f08363cc2ca"
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