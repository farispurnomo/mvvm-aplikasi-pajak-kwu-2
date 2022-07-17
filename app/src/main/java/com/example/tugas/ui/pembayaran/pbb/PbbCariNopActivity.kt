package com.example.tugas.ui.pembayaran.pbb

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.example.tugas.R
import com.example.tugas.databinding.ActivityPbbCariNopBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.base.BaseActivity
import com.example.tugas.utils.extensions.formatRibuan

class PbbCariNopActivity : BaseActivity<PbbCariNopViewModel, ActivityPbbCariNopBinding>() {

    override fun getViewBinding(): ActivityPbbCariNopBinding =
        ActivityPbbCariNopBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.btCariNop.setOnClickListener {
            viewModel.onGetTahunTagihan(binding.etNop.text.toString())
        }

        binding.btSubmit.setOnClickListener {
            val nop = binding.etNop.text.toString()
            val tahun = binding.spTahun.selectedItem.toString()
            viewModel.onGetDetailNop(nop, tahun)
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.tahunTagihan.observe(this) {
            if (it.isNotEmpty()) {
                binding.spTahun.adapter = ArrayAdapter(
                    this@PbbCariNopActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    resources.getStringArray(R.array.spinner_array)
                )

                binding.tvTahun.visibility = View.VISIBLE
                binding.spTahun.visibility = View.VISIBLE
                binding.btCariNop.visibility = View.GONE
                binding.btSubmit.visibility = View.VISIBLE
            }
        }

        viewModel.detailTagihan.observe(this) {
            val dialog = MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT))
                .customView(R.layout.dialog_detail_tagihan)
                .cancelable(true)

            val customView = dialog.getCustomView()

            val tvNop = customView.findViewById<AppCompatTextView>(R.id.tvNop)
            val tvNamaWp = customView.findViewById<AppCompatTextView>(R.id.tvNamaWp)
            val tvLokasi = customView.findViewById<AppCompatTextView>(R.id.tvLokasi)
            val tvProvinsi = customView.findViewById<AppCompatTextView>(R.id.tvProvinsi)
            val tvKota = customView.findViewById<AppCompatTextView>(R.id.tvKota)
            val tvKecamatan = customView.findViewById<AppCompatTextView>(R.id.tvKecamatan)
            val tvKelurahan = customView.findViewById<AppCompatTextView>(R.id.tvKelurahan)
            val tvLuasTanah = customView.findViewById<AppCompatTextView>(R.id.tvLuasTanah)
            val tvLuasBangunan = customView.findViewById<AppCompatTextView>(R.id.tvLuasBangunan)
            val tvTahunPajak = customView.findViewById<AppCompatTextView>(R.id.tvTahunPajak)
            val tvJatuhTempo = customView.findViewById<AppCompatTextView>(R.id.tvTanggalJatuhTempo)
            val tvPajakPokok = customView.findViewById<AppCompatTextView>(R.id.tvPajakPokok)
            val tvDenda = customView.findViewById<AppCompatTextView>(R.id.tvDenda)
            val tvBiayaAdmin = customView.findViewById<AppCompatTextView>(R.id.tvBiayaAdmin)
            val tvTotalBayar = customView.findViewById<AppCompatTextView>(R.id.tvTotalPembayaran)
            val btKonfirmasi = customView.findViewById<AppCompatButton>(R.id.btKonfirmasi)

            tvNop.text = it.nop
            tvNamaWp.text = it.nama_wp
            tvLokasi.text = it.lokasi
            tvProvinsi.text = it.provinsi
            tvKota.text = it.kota
            tvKecamatan.text = it.kecamatan
            tvKelurahan.text = it.kelurahan
            tvLuasTanah.text = it.luas_tanah
            tvLuasBangunan.text = it.luas_bangunan
            tvTahunPajak.text = it.tahun_pajak
            tvJatuhTempo.text = it.jatuh_tempo
            tvPajakPokok.text = it.pajak_pokok.formatRibuan()
            tvDenda.text = it.denda.formatRibuan()
            tvBiayaAdmin.text = it.biaya_admin.formatRibuan()
            tvTotalBayar.text = it.total_pembayaran.formatRibuan()
            btKonfirmasi.setOnClickListener {
                dialog.dismiss()
                showMessage("Berhasil dibayarkan")
                finish()
            }

            dialog.show { cornerRadius(16f) }
        }
    }

}