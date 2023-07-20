//package jp.ac.ecc.se.on_project;
//
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.ViewHolder> {
//
//    private List<String> sentences;
//    private List<Integer> imageIds;
//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public SentenceAdapter(List<String> sentences, List<Integer> imageIds, OnItemClickListener listener) {
//        this.sentences = sentences;
//        this.imageIds = imageIds;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
////    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sentence, parent, false);
//////        return new ViewHolder(view);
////    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String sentence = sentences.get(position);
//        holder.bind(sentence);
//
//        holder.itemView.setOnClickListener(v -> {
//            if (listener != null) {
//                listener.onItemClick(position);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return sentences.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
////        TextView sentenceTextView;
////        ImageView imageView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
////            sentenceTextView = itemView.findViewById(R.id.sentenceTextView);
////            imageView = itemView.findViewById(R.id.imageView);
//        }
//
//        public void bind(String sentence) {
////            sentenceTextView.setText(sentence);
//        }
//    }
//}
