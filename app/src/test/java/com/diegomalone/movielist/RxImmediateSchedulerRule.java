package com.diegomalone.movielist;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class RxImmediateSchedulerRule implements TestRule {

    private Scheduler immediate = new Scheduler() {
        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(new Executor() {
                @Override
                public void execute(Runnable command) {
                    command.run();
                }
            });
        }

        @Override
        public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit);
        }
    };

    private Function<Callable<Scheduler>, Scheduler> scheduler = new Function<Callable<Scheduler>, Scheduler>() {
        @Override
        public Scheduler apply(Callable<Scheduler> schedulerCallable) {
            return immediate;
        }
    };

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setInitIoSchedulerHandler(scheduler);
                RxJavaPlugins.setInitIoSchedulerHandler(scheduler);
                RxJavaPlugins.setInitComputationSchedulerHandler(scheduler);
                RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler);
                RxJavaPlugins.setInitSingleSchedulerHandler(scheduler);
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler);

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}
