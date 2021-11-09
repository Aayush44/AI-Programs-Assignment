import java.util.*;
 
public class WaterJugBFS{
 
    class PairForm{
        int FirstJug;
        int SecondJug;
 
        PairForm(int f, int s){
            FirstJug = f;
            SecondJug = s;
        }
    }
 
    void BFS(int a, int b, int target)
    {
        // Using the 2d array to track the visited values without any repetition.
        int arr[][] = new int[1000][1000];
        for(int[] i : arr){
            Arrays.fill(i, -1);
        }
 
        boolean WaterDivide = false;
        Vector<PairForm> path = new Vector<PairForm>();
        
        // queue to maintain states
        Queue<PairForm> queue = new LinkedList<PairForm>(); 

        // Initialing with initial state
        queue.add(new PairForm( 0, 0 )); 
 
        while (!queue.isEmpty()) 
        {

            // current state
            PairForm u = queue.peek(); 
            
            // pop off used state
            queue.poll(); 
 
            // doesn't met jug constraints
            if ((u.FirstJug > a || u.SecondJug > b || u.FirstJug < 0 || u.SecondJug < 0))
                continue;
 
            // if this state is already visited
            if (arr[u.FirstJug][u.SecondJug] > -1)
                continue;
            
            // Adding the values in vector for completing the solution
            path.add(new PairForm(u.FirstJug, u.SecondJug ));
 
            // marking current state as visited
            arr[u.FirstJug][u.SecondJug] = 1;
             
            //System.out.println(m.get(new Pair(u.first, u.second)));
             
            // Reaching the solution state 
            if (u.FirstJug == target || u.SecondJug == target)
            {
                WaterDivide = true;
                if (u.FirstJug == target) 
                {
                    if (u.SecondJug != 0)
 
                        // fill final state
                        path.add(new PairForm(u.FirstJug, 0 ));
                }
                else 
                {
                    if (u.FirstJug != 0)
 
                        // fill final state
                        path.add(new PairForm( 0, u.SecondJug));
                }
 
                // print the solution path
                int PathSize = path.size();
                for (int i = 0; i < PathSize; i++)
                System.out.println("(" + path.get(i).FirstJug + ", " + path.get(i).SecondJug + ")");
                break; 
            }
            
            // Forming the intermidiate stage for reaching to the solution if we not reached to the final state.
            
            //Filling the Jug 2
            queue.add(new PairForm( u.FirstJug, b )); 

            // Filling the Jug 1
            queue.add(new PairForm( a, u.SecondJug )); 

            for (int WaterAmount = 0; WaterAmount <= Math.max(a, b); WaterAmount++) {
 
                // pour amount "WaterAmount" from Jug2 to Jug1
                int c = u.FirstJug + WaterAmount;
                int d = u.SecondJug - WaterAmount;
 
                // check if this state is possible or not
                if (c == a || (d == 0 && d >= 0))
                    queue.add(new PairForm( c, d ));
 
                // Pour amount "WaterAmount" from Jug 1 to Jug2
                c = u.FirstJug - WaterAmount;
                d = u.SecondJug + WaterAmount;
 
                // check if this state is possible or not
                if ((c == 0 && c >= 0) || d == b)
                    queue.add(new PairForm( c, d ));
            }
 
            queue.add(new PairForm( a, 0 )); // Empty Jug2
            queue.add(new PairForm( 0, b )); // Empty Jug1
        }
 
        // No, solution exists if ans=0
        if (!WaterDivide)
            System.out.print("No solution");
    }
 
    // Driver code
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value for Jug 1:");
        int Jug1 = sc.nextInt();

        System.out.println("Enter the value for Jug 2:");
        int Jug2 = sc.nextInt();

        System.out.println("Enter the target value");
        int target = sc.nextInt();
        
 
        System.out.println("Path from initial state " + "to solution state ::");
 
            WaterJugBFS object = new WaterJugBFS();
 
        object.BFS(Jug1, Jug2, target);
 
    }
}