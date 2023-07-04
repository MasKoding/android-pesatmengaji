package com.example.pesatmengaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pesatmengaji.Adapter.DoaAdapter
import com.example.pesatmengaji.Adapter.HadistAdapter
import com.example.pesatmengaji.data.Doa
import com.example.pesatmengaji.data.DoaItem
import com.example.pesatmengaji.data.Hadist
import com.example.pesatmengaji.data.HadistItem

class HadistActivity : AppCompatActivity() {
    lateinit var hadistList: Hadist
    lateinit var tampilHadist: CardView

    lateinit var rvHadist: RecyclerView
    lateinit var toolbarHadist: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadist)

        rvHadist = findViewById(R.id.rv_hadist)

        toolbarHadist = findViewById(R.id.toolbar_hadist)

        hadistList = Hadist()

        hadistList.add(
            HadistItem("اَللّٰهُمَّ اِنَّ الضُّحَآءَ ضُحَاءُكَ وَالْبَهَاءَ بَهَاءُكَ وَالْجَمَالَ جَمَالُكَ وَالْقُوَّةَ قُوَّتُكَ وَالْقُدْرَةَ قُدْرَتُكَ وَالْعِصْمَةَ عِصْمَتُكَ اَللّٰهُمَّ اِنْ كَانَ رِزْقِى فِى السَّمَآءِ فَأَنْزِلْهُ وَاِنْ كَانَ فِى اْلاَرْضِ فَأَخْرِجْهُ وَاِنْ كَانَ مُعَسَّرًا فَيَسِّرْهُ وَاِنْ كَانَ حَرَامًا فَطَهِّرْهُ وَاِنْ كَانَ بَعِيْدًا فَقَرِّبْهُ بِحَقِّ ضُحَاءِكَ وَبَهَاءِكَ وَجَمَالِكَ وَقُوَّتِكَ وَقُدْرَتِكَ آتِنِىْ مَآاَتَيْتَ عِبَادَكَ الصَّالِحِيْنَ","Doa Sholat Dhuha",1,"","Allaahumma innadh dhuhaa-a dhuhaa-uka walbahaa-a bahaa-uka wal jamaala jamaaluka wal quwwata quw watuka wal qudrata qudratuka wal ‘ishmatta ‘ishmatuk. Allaahumma in kaana rizqii fissamaa-i fa anzilhu wa in kaanafil ardhi fa-akhrijhu wa in kaana mu’assaran fayas sirhu wa in kaana haraaman fathahhirhu wa in kaana ba’iidan faqarribhu bihaqqi dhuhaa-ika wa bahaa-ika wa jamaalika wa quuwatika wa qudratika aatinii maa aataita ‘ibaadakash shalihiin","Ya Allah sesungguhnya waktu dhuha adalah waktu dhuhaMu, Keagungan adalah keagunganMu, keindahan adalah keindahanMu, Kekuatan adalah kekuatanMu, Kekuasaan adalah kekuasaanMu, dan Penjagaan adalah penjagaanMu. Ya Allah jika rezekiku masih ada di atas langit maka turunkanlah, dan jika masih di dalam bumi maka keluarkanlah, dan jika masih sukar maka mudahkanlah, dan jika masih haram maka sucikanlah, dan jika masih jauh maka dekatkanlah. Demi kebenaran waktu DhuhaMu, KeagunganMu, KeindahanMu, KekuatanMu dan KekuasaanMu, Limpahkanlah kepada kami apa saja yang telah Engkau limpahkan kepada hamba- hambaMu yang shaleh")
        )
        hadistList.add(
            HadistItem("اللَّهُمَّ أَنْتَ رَبِّى ، لاَ إِلَهَ إِلاَّ أَنْتَ ، خَلَقْتَنِى وَأَنَا عَبْدُكَ ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَىَّ وَأَبُوءُ بِذَنْبِى ، اغْفِرْ لِى ، فَإِنَّهُ لاَ يَغْفِرُ الذُّنُوبَ إِلاَّ أَنْتَ","Doa Sayyidul Istighfar",2,"","Allâhumma anta rabbî, lâ ilâha illâ anta khalaqtanî. Wa anâ ‘abduka, wa anâ ‘alâ ‘ahdika wa wa‘dika mastatha‘tu. A‘ûdzu bika min syarri mâ shana‘tu. Abû’u laka bini‘matika ‘alayya. Wa abû’u bidzanbî. Faghfirlî. Fa innahû lâ yaghfirudz dzunûba illâ anta","Ya Allâh, Engkau adalah Rabbku, tidak ada Tuhan (Ilah) yang berhak diibadahi dengan benar selain Engkau. Engkau yang menciptakan aku dan aku adalah hamba-Mu. Aku menetapi perjanjian-Mu dan janji-Mu sesuai dengan kemampuanku. Aku berlindung kepada-Mu dari keburukan perbuatanku, aku mengakui nikmat-Mu kepadaku dan aku mengakui dosaku kepada- Mu, maka ampunilah aku. Sebab tidak ada yang dapat mengampuni dosa selain Engkau")
        )

        rvHadist!!.layoutManager = LinearLayoutManager(this)

        rvHadist!!.adapter = HadistAdapter(hadistList)


        toolbarHadist.setNavigationOnClickListener{
            val i = Intent(this,HomeMainActivity::class.java)
            startActivity(i)
        }

    }
}