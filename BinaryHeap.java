public class BinaryHeap {
    private int[] arr;
    private int size;

    public BinaryHeap(){
        this.arr = new int[10];
        this.size = 0;
    }



    public void add(int n){
        try{
            arr[size] = n; //add n to the end of the array, then increment size
            size++;
            siftup(size-1);
        }
        catch (Exception e){
            //if out of bounds, grow array and call add again
            growArray();
            add(n);
        }
    }

    private void siftup(int index) {
        if (index > 0){
            int parent = (index-1)/2;
            boolean lessThan = comparePC(parent, index);
            if(lessThan){ //if true, swap them and call siftup with the new index
                swap(parent, index);
                siftup(parent);
            }
        }
    }


    private void swap(int n, int m){
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }


    public int remove(){
        int n = arr[0];//store root
        size--;
        if(size > 0) {
            arr[0] = arr[size - 1]; //move last leaf to root position
            //decrement size because we removed one; effectively removes the last element as well, but it is in pos 0
            //as well so its ok
            arr[size] = 0;
//            size--;
            siftdown(0);
        }

        return n;
    }

    private void siftdown(int n){
        //n is the index of the item we are sifting down
        //at the start, n == 0
       int childL = n*2 + 1;
       int childR = childL+1;
       int min = childL; //set the min value to be the left child for now (default in case there is no right child)

       if(childL < size) {
           if (childR < size) { //if there is a right child,
               min = compareLR(childL, childR); //changes min to index of smaller child
           }

           if (arr[n] > arr[min]) {
               swap(n, min); //swap parent and child
               n = min; //set index of parent to index of child
               siftdown(n);
           }
       }
    }


    private void growArray() {
//        System.out.println("size: " + size);
//        System.out.println("arr.length: " + arr.length);
        int[] newArray = new int[arr.length * 2];
        int newIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            newArray[newIndex] = temp;
//            System.out.println("inside grow array for loop");
//            System.out.println("i: " + i);
//            System.out.println("newIndex: " + newIndex);
//            System.out.println("arr[i]" + arr[i]);
//            System.out.println("newArray[newIndex]: " + newArray[newIndex]);
            newIndex++;
        }
//        System.out.println("ended for loop");
//        System.out.println("arr");
//        for (int j = 0; j < size; j++) {
//            System.out.println(j + arr[j]);
//        }
//        System.out.println("newArray");
//        for (int j = 0; j < newArray.length; j++) {
//            System.out.println(j + newArray[j]);
//        }
        arr = newArray;
    }

    private int compareLR(int l, int r){
            if(arr[l] <= arr[r])
                return l;
            else{ //if arr[r] < arr[l]
                return r;
            }
        }

    private boolean comparePC(int p, int c){
        //compares parent and child values
        if(arr[p] > arr[c])
            return true;
        else
            return false;
    }


    public void printArray(){
        for(int i = 0; i < size; i++){
            System.out.println(arr[i]);
        }
    }

}
