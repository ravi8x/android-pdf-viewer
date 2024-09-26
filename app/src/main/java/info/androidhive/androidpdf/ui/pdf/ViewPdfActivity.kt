package info.androidhive.androidpdf.ui.pdf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.androidhive.androidpdf.R
import info.androidhive.androidpdf.databinding.ActivityViewPdfBinding

class ViewPdfActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityViewPdfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewPdfFragment.newInstance(intent.extras))
                .commitNow()
        }
    }
}