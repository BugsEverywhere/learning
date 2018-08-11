package indi.simon.sparklearn


/**
  * Created with IntelliJ IDEA.
  * User: Simon
  * Date:
  * Time:
  */
object MyObject {

  def testFlatMap():Unit = {

    var li=List(1,2,3,4)
    var res =li.flatMap(x=> x match {
      case 3 => List(3.1,3.2)
      case _ =>List(x*2)
    })
    println(res)

  }

  def testMap():Unit = {

    val li= List(1,2,3,4)
    var res2 =li.map(x=> x match {
      case 3 =>List(3.1,3.2)
      case _ =>x*2
    })
    println(res2)

  }

  def main(args: Array[String]): Unit = {

    testFlatMap();
    this.testMap()

  }


}
