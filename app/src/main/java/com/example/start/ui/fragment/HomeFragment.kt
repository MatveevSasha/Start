package com.example.start.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.start.R
import com.example.start.data.Film
import com.example.start.databinding.FragmentHomeBinding
import com.example.start.ui.activity.MainActivity
import com.example.start.ui.adapter.FilmListRecyclerAdapter
import com.example.start.utils.TopSpacingItemDecoration

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val filmsDataBase = listOf(
        Film(
            title = "Дюна",
            poster = R.drawable.poster_duna,
            description = "Человечество расселилось по далёким планетам, а за власть над обитаемым пространством постоянно борются разные могущественные семьи. В центре противостояния оказывается пустынная планета Арракис. Там обитают гигантские песчаные черви, а в пещерах затаились скитальцы-фремены, но её главная ценность — спайс, самое важное вещество во Вселенной. Тот, кто контролирует Арракис, контролирует спайс, а тот, кто контролирует спайс, контролирует Вселенную."
        ),
        Film(
            title = "Джентельмены",
            poster = R.drawable.poster_djentelmenu,
            description = "Талантливый выпускник Оксфорда, применив свой уникальный ум и невиданную дерзость, придумал нелегальную схему обогащения с использованием поместья обедневшей английской аристократии. Однако когда он решает продать свой бизнес влиятельному клану миллиардеров из США, на его пути встают не менее обаятельные, но жесткие джентльмены. Намечается обмен любезностями, который точно не обойдется без перестрелок и парочки несчастных случаев."
        ),
        Film(
            title = "Форсаж",
            poster = R.drawable.poster_forsazh,
            description = "Когда кривая дорожка заставляет скрывающегося от правосудия Дома Торетто вернуться в Лос-Анджелес, вражда с агентом Брайаном О’Коннером вспыхивает с новой силой. Но у парочки обнаруживается общий противник, и Дому с Брайаном приходится заключить перемирие в надежде одержать над ним победу. Грабя конвои, роя подкопы и пересекая границы, они приходят к идеальной форме отмщения — педали газа, вдавленной до упора."
        ),
        Film(
            title = "Один дома",
            poster = R.drawable.poster_odindoma,
            description = "Американское семейство отправляется из Чикаго в Европу, но в спешке сборов бестолковые родители забывают дома… одного из своих детей. Юное создание, однако, не теряется и демонстрирует чудеса изобретательности. И когда в дом залезают грабители, им приходится не раз пожалеть о встрече с милым крошкой."
        ),
        Film(
            title = "Друзья",
            poster = R.drawable.poster_druzya,
            description = "американский комедийный телесериал, ставший культовым, рассказывающий о жизни шестерых друзей в Нью-Йорке. Сериал исследует их взаимоотношения, любовь, карьеру, взлеты и падения, а также поиски себя. Он известен своим юмором, обаянием персонажей и актуальными для многих зрителей темами. "
        ),
        Film(
            title = "Леон",
            poster = R.drawable.poster_leon,
            description = "французский криминальный драматический боевик 1994 года, снятый Люком Бессоном. Главный герой, Леон, — профессиональный убийца, живущий уединенной жизнью в Нью-Йорке. Его спокойное существование нарушается, когда он становится опекуном двенадцатилетней Матильды, чья семья была убита коррумпированным офицером полиции Стэнсфилдом. "
        ),
        Film(
            title = "Волк с Уолл-стрит",
            poster = R.drawable.poster_volksuoltstrit,
            description = "Джордан Белфорт основал одну из крупнейших брокерских контор в 1987 году, но десять лет спустя был осужден за отмывание денег и ряд прочих финансовых преступлений. Автор справился с алкогольной и наркотической зависимостью, выработанной за время махинаций на Уолл-стрит, написал две книги и теперь читает лекции о том, как достичь успеха."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация адаптера
        filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
            override fun click(film: Film) {
                (requireActivity() as MainActivity).launchDetailsFragment(film)
            }
        })

        // Настройка RecyclerView
        with(binding.mainRecycler) {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(TopSpacingItemDecoration(8))
        }

        // Добавляем данные в адаптер
        filmsAdapter.addItems(filmsDataBase)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

