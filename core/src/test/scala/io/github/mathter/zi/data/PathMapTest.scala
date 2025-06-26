package io.github.mathter.zi.data

import io.github.mathter.zi.data.PathMapTest.randomStrings
import io.github.mathter.zi.path.Path
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.{Assertions, Test}

import scala.collection.mutable

class PathMapTest {
  @Test
  def testGet(): Unit = {
    val title: Path = "title"
    val titleValue = randomStrings.nextGraph(10)

    val authors: Path = "authors"
    val authorName: Path = "a/name"
    val authorsName: Path = "authors/a/name"
    val authorLastName: Path = "a/lastName"
    val authorsLastName = "authors/a/lastName"

    val authorName0 = randomStrings.nextAlphabetic(10)
    val authorLastName0 = randomStrings.nextAlphabetic(10)

    val authorName1 = randomStrings.nextAlphabetic(10)
    val authorLastName1 = randomStrings.nextAlphabetic(10)

    val isbn: Path = "usbn"
    val isbnValue = randomStrings.nextAlphabetic(10)

    val year: Path = "year"
    val yearValue = 2024

    val bookPathMap = PathMap.empty
    bookPathMap(title) = titleValue

    val author0PathMap = PathMap.empty
    author0PathMap(authorName) = authorName0
    author0PathMap(authorLastName) = authorLastName0
    bookPathMap(authors) = author0PathMap

    val author1PathMap = PathMap.empty
    author1PathMap(authorName) = authorName1
    author1PathMap(authorLastName) = authorLastName1
    bookPathMap(authors) = author1PathMap

    bookPathMap(isbn) = isbnValue
    bookPathMap(year) = yearValue

    Assertions.assertEquals(titleValue, bookPathMap(title))

    val authorsResultPathList = bookPathMap[List[?]](authors)
    Assertions.assertNotNull(authorsResultPathList)
    Assertions.assertTrue(authorsResultPathList.isInstanceOf[List[?]])

    val authorResult0: PathMap = authorsResultPathList(0).asInstanceOf[PathMap]
    Assertions.assertNotNull(authorResult0)
    Assertions.assertEquals(authorName0, authorResult0(authorName))
    Assertions.assertEquals(authorLastName0, authorResult0(authorLastName))

    val authorResult1: PathMap = authorsResultPathList(1).asInstanceOf[PathMap]
    Assertions.assertNotNull(authorResult1)
    Assertions.assertEquals(authorName1, authorResult1(authorName))
    Assertions.assertEquals(authorLastName1, authorResult1(authorLastName))

    Assertions.assertEquals(isbnValue, bookPathMap(isbn))
    Assertions.assertEquals(yearValue.asInstanceOf[Any], bookPathMap(year))
  }
}

object PathMapTest {
  val randomStrings = RandomStringUtils.insecure()
}
