package team.uptech.food.bot.utils.extensions

import team.uptech.food.bot.presentation.dsl.models.BuilderMarker
import team.uptech.food.bot.presentation.dsl.models.Element
import team.uptech.food.bot.presentation.dsl.models.Section
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
@BuilderMarker
inline fun <reified T : Section> MutableList<in T>.block(block: T.() -> Unit) =
  getInstance(T::class).apply(block).also { add(it) }

fun <T : Section> getInstance(cls: KClass<T>) = cls.createInstance()

@BuilderMarker
inline fun <reified T : Element> MutableList<in T>.element(block: T.() -> Unit) =
  getInstance(T::class).apply(block).also { add(it) }

fun <T : Element> getInstance(cls: KClass<T>) = cls.createInstance()