package com.example.taskmaster.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;
import com.example.taskmaster.Activities.MainActivity;
import com.example.taskmaster.Activities.TaskDetail;

import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Task> allTasks=new ArrayList<Task>();

    public TaskAdapter(List<Task> allTasks) {
        this.allTasks = allTasks;
    }



    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public Task task;
        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task=allTasks.get(position);
        TextView title=holder.itemView.findViewById(R.id.titleTextView);
        TextView body=holder.itemView.findViewById(R.id.bodyTextView);
        TextView state=holder.itemView.findViewById(R.id.stateTextView);
        Button goToDetails =holder.itemView.findViewById(R.id.goToDetailsButton);

        title.setText(holder.task.getTitle());
        body.setText(holder.task.getBody());
        state.setText(holder.task.getState());
        goToDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToDetailsPage=new Intent(v.getContext(),TaskDetail.class);
                goToDetailsPage.putExtra("taskTitle",holder.task.getTitle());
                goToDetailsPage.putExtra("taskBody",holder.task.getBody());
                goToDetailsPage.putExtra("taskState",holder.task.getState());
                v.getContext().startActivity(goToDetailsPage);
            }
        });


    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }
}
