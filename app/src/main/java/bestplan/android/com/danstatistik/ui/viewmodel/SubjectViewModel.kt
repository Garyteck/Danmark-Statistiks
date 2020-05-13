package bestplan.android.com.danstatistik.ui.viewmodel

import androidx.lifecycle.ViewModel
import bestplan.android.com.danstatistik.data.repository.SubjectRepository
import javax.inject.Inject


class SubjectViewModel @Inject constructor(private val repository: SubjectRepository) :
    ViewModel() {

    val subjects by lazy { repository.getSubjects() }


}