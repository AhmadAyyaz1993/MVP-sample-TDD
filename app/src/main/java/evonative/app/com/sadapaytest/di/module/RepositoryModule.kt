package evonative.app.com.sadapaytest.di.module
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import evonative.app.com.sadapaytest.data.repository.IReposRepository
import evonative.app.com.sadapaytest.data.repository.ReposRepositroy

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {
    @Binds
    fun getRepository(reposRepositroy: ReposRepositroy): IReposRepository
}