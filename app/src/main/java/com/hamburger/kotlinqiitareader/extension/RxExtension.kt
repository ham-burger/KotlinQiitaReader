package com.hamburger.kotlinqiitareader.extension

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.subscribeOnIOThread(): Observable<T> = subscribeOn(Schedulers.io())
fun <T> Observable<T>.observeOnMainThread(): Observable<T> = observeOn(AndroidSchedulers.mainThread())
fun <T> Observable<T>.completeWhenError(): Observable<T> = onErrorResumeNext(Observable.empty<T>())
fun <T> Single<T>.subscribeOnIOThread(): Single<T> = subscribeOn(Schedulers.io())
fun <T> Single<T>.observeOnMainThread(): Single<T> = observeOn(AndroidSchedulers.mainThread())