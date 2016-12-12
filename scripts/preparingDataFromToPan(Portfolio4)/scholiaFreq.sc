//This script allows you to enter in a version of Iliadic scholia that is separate such that every 
//line of the plain text file is a different scholia. From that text file, you can find out the frequency of the
//types of scholia in that particular file. 

//Usage: amm fileName

import scala.io.Source

@main
def scholiaBreakdown (file: String) {

  val origFile = Source.fromFile(file).getLines.toVector

  val totalScholia: Double = origFile.size 

  val mainScholia: Double = origFile.filter(_.contains("6.msA.")).size

  val interlinear: Double = origFile.filter(_.contains("6.msAil.")).size

  val intermarg: Double = origFile.filter(_.contains("6.msAim.")).size

  val interior: Double = origFile.filter(_.contains("6.msAint.")).size

  val ext: Double = origFile.filter(_.contains("6.msAext.")).size

  //require (totalScholia == mainScholia + interlinear + intermarg + interior + ext)

  println("\nThis is scholia set " + file + " contains:\nmain scholia: "
    + mainScholia + " (" + (mainScholia / totalScholia) + ")\nintermarginal scholia: "
     + intermarg + " (" + (intermarg / totalScholia) + ")\ninterior scholia: "
     + interior + " (" + (interior / totalScholia) + ")\ninterlinear scholia: "
     + interlinear + " (" + (interlinear / totalScholia) + ")\nexterior scholia: "
     + ext + " (" + (ext / totalScholia) + ")\nIn total there are " + totalScholia + " scholia.")

}
