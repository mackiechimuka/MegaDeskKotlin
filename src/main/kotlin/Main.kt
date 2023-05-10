fun main(args: Array<String>) {
    println("Hello World!")
    var GetQuote = DeskQuote()
    println("Enter Width and Depth of the Desk below which should be numbers greater than 0 ")
    var EnterWidth:Int = readLine()!!.toInt()
    while (EnterWidth <=0){
        println("Width should be greater than 0 and an Interger")
        EnterWidth = readLine()!!.toInt()
    }
    var EnterDepth:Int = readLine()!!.toInt()
    while (EnterDepth <=0){
        println("Depth should be greater than 0 and an Interger")
        EnterDepth = readLine()!!.toInt()
    }

    println("Enter number of drawers in numbers")
    var EnterNumDraw:Int = readLine()!!.toInt()
    println("Enter Surface Material from the following list \n Oak \n Laminate \n Pine \n Rosewood \n Veneer ")
    var surfaceMatArray:Array<String> = arrayOf("Oak","Laminate","Pine","Rosewood","Veneer")
    var EnterSurfaceMat:String = readLine()!!.toString().replaceFirstChar { it.uppercase() }
    while (EnterSurfaceMat !in surfaceMatArray){
        println("Please enter the correct Surface Material from the following list \n Oak \n Laminate \n Pine \n Rosewood \n Veneer ")
        EnterSurfaceMat = readLine()!!.toString().replaceFirstChar { it.uppercase() }
    }
    GetQuote.desk.setWidth(EnterWidth)
    GetQuote.desk.setDepth(EnterDepth)
    GetQuote.desk.setNumDrawer(EnterNumDraw)
    GetQuote.desk.setMaterial(EnterSurfaceMat)

    println("Enter Customer Name")
    var customerNam = readLine()!!.toString()
    GetQuote.setCustomerName(customerNam)
    println("Enter the number of Days the Order to be delivered from 3,5,7 or 14 ")
    var rushOrder = readLine()!!.toInt()
    GetQuote.setRushOrder(rushOrder)
    var total = GetQuote.CalTotalPrice()
    println("Total Desk Quote: ${total} ")

    println("List of all Desk Quotes by customers")
    GetQuote.getCustomerOrders()
}

class Desk {
    private var width: Int = 0;
    private var depth: Int = 0
    private var numDrawers: Int = 0
    private var material: String = "Enter your material"

    fun setWidth(wid: Int) {
        this.width = wid
    }

    fun getWidth(): Int {
        return this.width
    }

    fun setDepth(dep:Int) {
        this.depth = dep
    }

    fun getDepth(): Int {
        return this.depth
    }

    fun setNumDrawer(numbDraw: Int) {
        this.numDrawers = numbDraw;
    }

    fun getNumDrawer(): Int {
        return this.numDrawers
    }

    fun setMaterial(mat: String) {
        this.material = mat;
    }

    fun getMaterial(): String {
        return this.material
    }


}

class DeskQuote {
    public var desk: Desk = Desk()
    private var rushOrder: Int = 14
    private var totalPrice: Double = 0.0
    private var customerName: String = ""
    private var customerOrders = mutableMapOf("Mephis Micheals" to 40.80)

    fun setRushOrder(rushOrd:Int){
        this.rushOrder = rushOrd
    }

    fun getRushOrder(): Int {
        return this.rushOrder
    }

    fun setCustomerName(name: String) {
        this.customerName = name
    }

    fun getCustomerName(): String {
        return this.customerName
    }

    fun getCustomerOrders() {

        if (customerOrders.containsKey(customerName)) {
            var count = 1
            for (col in 1..100) {
                count += 1
            }

            customerOrders[this.customerName + count.toString()] = totalPrice

        } else {
            this.customerOrders[this.customerName] = this.totalPrice

        }
        customerOrders.forEach {
            println("CustomerName: ${it.key} Total Quote : ${it.value}")
        }


    }

    fun CalTotalPrice():Double{
        var basePrice:Double =500.00
        var materialPrice:Double = 0.0
        var drawerPrice:Double =0.0
        var rushOrderPrice:Double = 0.0
        var extraSurfacePrice:Double = 0.0
        val lowerArea = 1000
        val upperArea = 2000
        val surfaceArea = this.desk.getWidth() * this.desk.getDepth()
        if(surfaceArea < lowerArea){
            var material = desk.getMaterial().replaceFirstChar { it.uppercase() }
            when(material) {
                "Oak" -> materialPrice += 200
                "Laminate" -> materialPrice += 100
                "Pine" -> materialPrice +=50
                "Rosewood" -> materialPrice += 300
                "Veneer" -> materialPrice += 125
            }
            drawerPrice += desk.getNumDrawer() *50.0
            when(rushOrder){
                3 -> rushOrderPrice += 60.0
                5 -> rushOrderPrice += 40.0
                7 -> rushOrderPrice += 30.0
            }
             totalPrice += rushOrderPrice +drawerPrice + materialPrice +basePrice

        }
        else if(surfaceArea >= lowerArea && surfaceArea <= upperArea){
            var extraArea = surfaceArea - 1000
            extraSurfacePrice =  extraArea * 1.00
            var material = desk.getMaterial().replaceFirstChar { it.uppercase() }
            when(material){
                "Oak" -> materialPrice += 200
                "Laminate" -> materialPrice += 100
                "Pine" -> materialPrice +=50
                "Rosewood" -> materialPrice += 300
                "Veneer" -> materialPrice += 125
            }
            drawerPrice += desk.getNumDrawer() * 50.00
            when(rushOrder){
                3 -> rushOrderPrice += 70.00
                5 -> rushOrderPrice += 50.00
                7 -> rushOrderPrice += 35.00
            }
            totalPrice += basePrice +materialPrice +extraSurfacePrice +drawerPrice+drawerPrice
        }
        else{
            var extraArea = surfaceArea - 1000
            extraSurfacePrice =  extraArea * 1.00
            var material = desk.getMaterial().replaceFirstChar { it.uppercase() }
            when(material){
                "Oak" -> materialPrice += 200
                "Laminate" -> materialPrice += 100
                "Pine" -> materialPrice +=50
                "Rosewood" -> materialPrice += 300
                "Veneer" -> materialPrice += 125
            }
            drawerPrice += desk.getNumDrawer() * 50.00
            when(rushOrder){
                3 -> rushOrderPrice += 80.00
                5 -> rushOrderPrice += 60.00
                7 -> rushOrderPrice += 40.00
            }
            totalPrice += basePrice +materialPrice +extraSurfacePrice +drawerPrice+drawerPrice
        }
        return  totalPrice
    }
}