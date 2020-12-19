package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse

object DataDummy {

    fun generateDummyEmpty(): List<MovieEntity> = ArrayList<MovieEntity>()

    fun generateITestDummyMovie(): List<MovieResponse> {

        val courses = ArrayList<MovieResponse>()

        courses.add(MovieResponse(765123,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/zMcEalkxEiRjvmijliLBk0sYern.jpg",
            "Christmas Crossfire",
            "2020",
            5.0,
            "Comedy, Crime, Thriller",
            "A man foils an attempted murder, then flees the crew of would-be killers along with their intended target as a woman he's just met tries to find him.",
            "2020-12-04"))

        courses.add(MovieResponse(590706,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
            "Jiu Jitsu",
            "2020",
                5.8,
            "Action, Fantasy, Science Fiction",
            "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
            "2020-11-20"))

        return courses
    }

    fun generateITestDummyTv(): List<TvResponse> {

        val tv = ArrayList<TvResponse>()

        tv.add(TvResponse(82856,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/non7AnwMaloVU0OtenLqKyq8Zg3.jpg",
            "The Mandalorian",
            "2019",
                8.5,
            "Sci-Fi & Fantasy, Action & Adventure",
            "The Mandalorian and his allies attempt a daring rescue.",
            "2019-11-12"))

        tv.add(TvResponse(97180,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg",
            "Selena: The Series",
            "2020",
            7.5,
            "Drama",
            "Selena feels conflicted about her secret relationship with Chris. Meanwhile, AB finds inspiration in a memory of a plastic flower and lost love.",
            "2020-12-04"))

        return tv
    }

    fun generateDummyResponseMovie(): List<MovieResponse> {

        val courses = ArrayList<MovieResponse>()

        courses.add(MovieResponse(1,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/j8MRnCjuN7kpM8w3B5hM5mrvTaE.jpg",
            "A Start Is Born",
            "2018",
            8.3,
            "Roman, Musik",
            "Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
            "19 Oktober 2018"))
        courses.add(MovieResponse(2,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/j8MRnCjuN7kpM8w3B5hM5mrvTaE.jpg",
            "Alita: Battle Angel",
            "2019",
            7.1,
            "Aksi, Cerita Fiksi, Petualangan",
            "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa.",
            "14 Februari 2019"))
        courses.add(MovieResponse(3,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/j8MRnCjuN7kpM8w3B5hM5mrvTaE.jpg",
            "Aquaman",
            "2018",
            6.9,
            "Aksi, Petualangan, Fantasi",
            "Dulunya rumah bagi peradaban paling maju di Bumi, Atlantis sekarang menjadi kerajaan bawah air yang diperintah oleh Raja Orm yang haus kekuasaan. Dengan pasukan besar yang dimilikinya, Orm berencana untuk menaklukkan orang-orang samudra yang tersisa dan kemudian dunia permukaan. Yang menghalangi jalannya adalah Arthur Curry, saudara setengah manusia Orm, saudara setengah Atlantis dan pewaris sejati takhta.",
            "21 Desember 2018"))
        courses.add(MovieResponse(4,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/j8MRnCjuN7kpM8w3B5hM5mrvTaE.jpg",
            "Bohemian Rhapsody",
            "2018",
            8.0,
            "Drama, Musik",
            "Penyanyi Freddie Mercury, gitaris Brian May, drummer Roger Taylor, dan gitaris bass John Deacon menggemparkan dunia musik ketika mereka membentuk band rock 'n' roll Queen pada tahun 1970. Lagu-lagu hit menjadi klasik instan. Ketika gaya hidup Mercury yang semakin liar mulai lepas kendali, Queen segera menghadapi tantangan terbesarnya - menemukan cara untuk menjaga band tetap bersama di tengah kesuksesan dan kelebihan.",
            "02 November 2018"))
        courses.add(MovieResponse(5,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/j8MRnCjuN7kpM8w3B5hM5mrvTaE.jpg",
            "Cold Persuit",
            "2019",
            5.6,
            "Aksi, Kejahatan, Cerita Seru",
            "Kehidupan keluarga yang tenang dari Nels Coxman, seorang pengemudi bajak salju, berubah setelah pembunuhan putranya. Nels memulai perburuan dendam untuk Viking, raja obat bius yang dianggapnya bertanggung jawab atas pembunuhan itu, melenyapkan rekan Viking satu per satu. Saat Nels semakin dekat dengan Viking, tindakannya membawa konsekuensi yang lebih tidak terduga dan kejam, karena ia membuktikan bahwa balas dendam ada dalam eksekusi.",
            "08 Februari 2019"))

        return courses
    }

    fun generateDummyResponseTv(): List<TvResponse> {

        val tv = ArrayList<TvResponse>()

        tv.add(TvResponse(1,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "The Arrow",
            "2012",
            6.5,
            "Kejahatan, Drama, Misteri, Aksi & Petualangan",
            "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow.",
            "10 Oktober 2012"))

        tv.add(TvResponse(2,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "Dragon Ball",
            "1986",
            8.0,
            "Komedi, Sci-fi & Fantasy, Animasi, Aksi & Petualangan",
            "Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.",
            "20 Desember 1986"))

        tv.add(TvResponse(3,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "Fairy Tail",
            "2009",
            7.6,
            "Komedi, Sci-fi & Fantasy, Animasi, Aksi & Petualangan",
            "Lucy adalah seorang gadis berusia 17 tahun, yang ingin menjadi penyihir sejati. Suatu hari ketika mengunjungi Kota Harujion, dia bertemu dengan Natsu, seorang pemuda yang mudah sakit dengan segala jenis transportasi. Tapi Natsu bukan sembarang anak biasa, dia adalah anggota dari salah satu guild penyihir paling terkenal di dunia: Fairy Tail.",
            "12 Oktober 2009"))

        tv.add(TvResponse(4,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "Family Guy",
            "1999",
            6.9,
            "Animasi, Komedi",
            "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.",
            "31 Januari 1999"))

        tv.add(TvResponse(5,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "The Flash",
            "2014",
            7.6,
            "Drama, Sci-Fi & Fantasy",
            "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya \"manusia meta\" yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
            "7 Oktober 2014"))

        return tv
    }
}