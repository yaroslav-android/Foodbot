package team.uptech.food.bot.utils

import team.uptech.food.bot.presentation.models.Element
import team.uptech.food.bot.presentation.models.Section
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * Use only within `blocks` only!
 *
 * Example usage:
 * ```
 *  blocks {
 *    item<SectionInput> {
 *      blockId = "some_block"
 *
 *      label {
 *        text = "Amount"
 *      }
 *    }
 *  }
 * ```
 */
inline fun <reified T : Section> MutableList<in T>.block(block: T.() -> Unit) =
  getInstance(T::class).apply(block).also { add(it) }

fun <T : Section> getInstance(cls: KClass<T>) = cls.createInstance()

inline fun <reified T : Element> MutableList<in T>.element(block: T.() -> Unit) =
  getInstance(T::class).apply(block).also { add(it) }

fun <T : Element> getInstance(cls: KClass<T>) = cls.createInstance()