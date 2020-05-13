package bestplan.android.com.danstatistik.ui.viewmodel

import androidx.lifecycle.ViewModel
import bestplan.android.com.danstatistik.data.repository.Repository
import bestplan.android.com.danstatistik.data.repository.SubjectRepository
import javax.inject.Inject

class TableViewModel @Inject constructor(
    private val repository: Repository,
    private val subjectRepository: SubjectRepository
) : ViewModel() {

    lateinit var id: String

    val tables by lazy { repository.getTables(id) }

    val subject by lazy { subjectRepository.getSubject(id) }


}