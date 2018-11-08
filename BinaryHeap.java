public class BinaryHeap {
    private int[] arr;
    private int size;

    public BinaryHeap(){
        this.arr = new int[10];
        this.size = 0;
    }



    public void add(int n){
        try{
            //first add to the end of the array, using size
            //if out of bounds, grow array and call add again
            arr[size++] = n; //add n to the end of the array, then increment size
            shiftUp(size-1);
        }
        catch (Exception e){
            //if out of bounds, grow array and call add again
            growArray();
            add(n);
        }
    }

    private void shiftUp (int index) {
        int parentIndex = (index - 1) / 2;
        if (parentIndex != 0) {
            if (arr[parentIndex] > arr[index]) //if the parent is greater, then swap places
                swap(parentIndex, index);
                shiftUp(parentIndex);
        }
    }


    private void swap(int n, int m){
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }


    public int remove(){
        int n = arr[0];//store root
        arr[0] = arr[size-1]; //move last leaf to root position
        size--; //decrement size because we removed one; effectively removes the last element as well, but it is in pos 0
        //as well so its ok

        shiftdown(0);
        return n;
    }

    /*
    * left_child = parent * 2 + 1;
    * right_child = parent * 2 + 2;
    */




    private void shiftdown(int n){
        //base case?
        int childL = n*2 + 1;
        int childR = n*2 + 2;

        if(arr[n] < arr[childL] && arr[n] < childR){ //if it is in the correct position, stop
            return;
        }
        int minVal = Math.min(arr[childL], arr[childR]); //find the smaller of the two children
        if(minVal == arr[childL]) { //if the left one is the smallest child
            swap(arr, n, childL);
            shiftdown(childL); //now the parent is at the child's index, call recursively using the new index
        }
        else{
            //if minval = arr[childR]
            swap(arr, n, childR);
            shiftdown(childR);
        }





//        int child = parent * 2 + 1;
//        if(arr[child + 1] < arr[child])
//            child++; //if the right child is less than the left child, then the index child points to the lesser child (r)
//        if(arr[parent] > arr[child])
//            swap(arr, child, parent);
//        shiftdown(child);


    }

    private void swap(int[] a, int one, int two){
        int temp = a[one];
        a[one] = two;
        a[two] = temp;
    }

    private void growArray(){
        int[] newArray = new int[arr.length*2];
        for (int i = 0; i < size; i++){
            newArray[i] = arr[i];
        }
        arr = newArray;
    }





}
