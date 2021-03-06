/*
 * Copyright (c) 2013-2017 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.snowplowanalytics

// Java
import java.math.BigDecimal
// Joda 
import org.joda.time._
// LRUCache
import com.twitter.util.SynchronizedLruMap
// oerclient
import forex.oerclient.OerResponseError

package object forex {
  /**
   * The key and value for each cache entry
   */
  type NowishCacheKey       = Tuple2[String, String] // source currency , target currency 
  type NowishCacheValue     = Tuple2[DateTime, BigDecimal] // timestamp, exchange rate 

  type EodCacheKey          = Tuple3[String, String, DateTime] // source currency, target currency, timestamp
  type EodCacheValue        = BigDecimal // exchange rate
  
  // The API request either returns exchange rates in BigDecimal representation
  // or OerResponseError if the request failed
  type ApiRequestResult     = Either[OerResponseError, BigDecimal]   
  
  /**
   * The two LRU caches we use
   */
  type NowishCache          = SynchronizedLruMap[NowishCacheKey, NowishCacheValue]
  type EodCache             = SynchronizedLruMap[EodCacheKey, EodCacheValue]

  type MaybeNowishCache     = Option[NowishCache]
  type MaybeEodCache        = Option[EodCache]
}
