package com.duongph.moneynote.domain

import com.duongph.moneynote.data.repo.CategoryRepoImpl
import com.duongph.moneynote.data.repo.MoneyRepoImpl
import com.duongph.moneynote.data.repo.local.CategoryLocalRepo
import com.duongph.moneynote.data.repo.local.MoneyLocalRepo
import com.duongph.moneynote.data.repo.remote.CategoryRemoteRepo
import com.duongph.moneynote.data.repo.remote.MoneyRemoteRepo
import com.duongph.moneynote.domain.repo.ICategoryRepo
import com.duongph.moneynote.domain.repo.IMoneyNoteRepo

object RepoFactory {

    fun getCategoryRepo(): ICategoryRepo {
        return CategoryRepoImpl(CategoryLocalRepo(), CategoryRemoteRepo())
    }

    fun getNoteRepo(): IMoneyNoteRepo {
        return MoneyRepoImpl(MoneyRemoteRepo(), MoneyLocalRepo())
    }
}