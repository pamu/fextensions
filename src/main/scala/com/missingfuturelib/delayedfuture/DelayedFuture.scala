/*
 * Copyright (c) 2016 Pamu Nagarjuna (http://pamu.github.io).
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.missingfuturelib.delayedfuture

import scala.concurrent.{ExecutionContext, Future}

sealed trait DelayedFuture[A] {

  val delayedFuture:  () => Future[A]

  def run(): Future[A] = delayedFuture()

  def map[B](f: A => B): DelayedFuture[B] = {
    DelayedFuture(delayedFuture().map(f))
  }

  def flatMap[B](f: A => DelayedFuture[B]): DelayedFuture[B] = {
    DelayedFuture(delayedFuture().flatMap(f(_).run()))
  }


}


object DelayedFuture {
  def apply[A](future: => Future[A]): DelayedFuture[A] = new DelayedFuture[A] {
    override val delayedFuture: () => Future[A] =  () => future
  }

  def apply[A](code: => A)(implicit ec: ExecutionContext): DelayedFuture[A] = new DelayedFuture[A] {
    override val delayedFuture: () => Future[A] = () => Future(code)
  }
}