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
            siftup();
        }
        catch (Exception e){
            //if out of bounds, grow array and call add again
            growArray();
            add(n);
        }
    }

    private void siftup() {
        int last = size-1;
        while(last > 0){
            int parent = (last-1)/2;
            boolean lessThan = comparePC(parent, last);
            if(lessThan){ //if true, swap them and call siftup with the new index
                swap(parent, last);
                last = parent;
            }
            else{
                break;
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
            siftdown();
        }
        return n;
    }

    private void siftdown(){
        int parent = 0;
        int childL = parent*2 + 1;
        while(childL < size){
            int min = childL; //set the min value to be the left child for now (default in case there is no right child)
            int childR = childL + 1;
            if(childR < size)
                min += compareLR(childL, childR);
            if (arr[parent] > arr[min]) {
                swap(parent, min); //swap parent and child
                parent = min; //set index of parent to index of child
                childL = parent*2 + 1;
            }
            else{
                break;
            }
        }



        //n is the index of the item we are sifting down
        //the first time siftdown is called, n == 0
//       int childL = n*2 + 1;
//       int childR = childL+1;
//       int min = childL; //set the min value to be the left child for now (default in case there is no right child)
//
//       if(childL < size) {
//           if (childR < size) { //if there is a right child,
//               min = compareLR(childL, childR); //changes min to index of smaller child
//           }
//
//           if (arr[n] > arr[min]) {
//               swap(n, min); //swap parent and child
//               n = min; //set index of parent to index of child
//               siftdown(n);
//           }
//       }
    }


    private void growArray() {
        int[] newArray = new int[arr.length * 2];
        int newIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            newArray[newIndex] = temp;
            newIndex++;
        }
        arr = newArray;
    }

    private int compareLR(int l, int r){
            if(arr[l] <= arr[r])
                return 0;
            else{ //if arr[r] < arr[l]
                return 1;
            }
        }

    private boolean comparePC(int p, int c){
        //compares parent and child values
        if(arr[p] > arr[c])
            return true;
        else
            return false;
    }

}
