import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun main() {
    println("Begin main")
    val observable = Observable.just(1, 2, 3, 2).subscribeOn(Schedulers.newThread())
    val observable2 = Observable.just(4, 5, 6).delay(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
//    val observableMerge = Observable.merge(observable, observable2)
//    observableMerge.subscribe {
//        println("data merge = $it")
//    }
//    val observableZip = Observable.zip(observable, observable2, { ob1, ob2 ->
//        ob1 + ob2
//    })
    val observableZip = Observable.zip(listOf(observable, observable2)) {
        "---- ${it.toList()}"
    }
    observableZip.subscribe {
        println("data zip = $it")
    }
    Thread.sleep(3000)
    println("End main")
}