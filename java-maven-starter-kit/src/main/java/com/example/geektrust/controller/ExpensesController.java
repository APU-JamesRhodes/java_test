package com.example.geektrust.controller;

import com.example.geektrust.model.Dues;
import com.example.geektrust.model.Transaction;
import com.example.geektrust.model.User;
import com.example.geektrust.model.response.*;
import com.example.geektrust.repository.UserRepository;
import com.example.geektrust.utils.CommandExtractor;

import java.util.ArrayList;

public class ExpensesController {

    private CommandExtractor extractor;

    public ExpensesController() {
        extractor = CommandExtractor.build();
    }

    public void run(String command, UserRepository repository) {
        Response response = extractor.response(command);
        if (response instanceof MoveInResponse) {
            MoveInResponse res = (MoveInResponse) response;
            if (repository.getSize() < 3) {
                Response update = repository.save(new User(res.getNameOfTheMember()));
                if (update instanceof ErrorResponse) {
                    System.out.println("FAILURE");
                } else if (update instanceof UserResponse) {
                    System.out.println("SUCCESS");
                }
            } else {
                System.out.println("HOUSEFUL");
            }
        } else if (response instanceof SpendResponse) {
            SpendResponse res = (SpendResponse) response;
            ArrayList<String> spendFor = new ArrayList<>(res.getSpendFor());
            String spendByUserName = res.getSpendBy();
            Response spendBy = repository.getByUserName(spendByUserName);
            if (spendBy instanceof UserResponse) {
                User spendByUser = ((UserResponse) spendBy).getUser();
                spendByUser.setSpend(new Transaction(res.getAmount() - res.getAmount() / (spendFor.size() + 1),
                                spendByUser.getUserName(),
                                spendFor
                        )
                );
                repository.save(spendByUser);
                int count = 0;
                for (int i = 0; i < spendFor.size(); i++) {
                    if (repository.find(spendFor.get(i))) {
                        count++;
                    }
                }
                if (count != spendFor.size()) {
                    System.out.println("MEMBER_NOT_FOUND");
                } else {
                    int totalAmount = res.getAmount();
                    int splitAmount = totalAmount / (spendFor.size() + 1);
                    //add dues
                    for (int i = 0; i < spendFor.size(); i++) {
                        Response userResponse = repository.getByUserName(spendFor.get(i));
                        if (userResponse instanceof UserResponse) {
                            User user = ((UserResponse) userResponse).getUser();
                            boolean find = false;
                            Dues duess = null;
                            for (Dues dues : user.getDues()) {
                                if (dues.getLentBy().equals(res.getSpendBy())) {
                                    find = true;
                                    duess = dues;
                                    break;
                                }
                            }
                            if (find) {
                                duess.setAmount(duess.getAmount() + splitAmount);
                                user.setDues(duess);
                                repository.save(user);
                            } else {
                                Dues d = new Dues(spendByUser.getUserName(), splitAmount);
                                user.setDues(d);
                                repository.save(user);
                            }
                        }
                    }
                    System.out.println("SUCCESS");
                }
            } else {
                System.out.println("MEMBER_NOT_FOUND");
            }
        } else if (response instanceof DuesResponse) {
            DuesResponse duesResponse = (DuesResponse) response;
            String owesPerson = duesResponse.getOwesBy();
            if (!repository.find(owesPerson)) {
                System.out.println("MEMBER_NOT_FOUND");
            } else {
                Response foundUser = repository.getByUserName(owesPerson);
                if (foundUser instanceof UserResponse) {
                    User user = ((UserResponse) foundUser).getUser();
                    //bo
                    //all dues for current user ex-bo->woody,andy
                    ArrayList<String> allDueUser = new ArrayList<>();
                    for (Dues due : user.getDues()) {
                        allDueUser.add(due.getLentBy());
                    }
                    int dueAmount = 0;
                    String lentUser = "";
                    for (Dues due : user.getDues()) {
                        Response foundDueUser = repository.getByUserName(due.getLentBy());
                        User dueUser = ((UserResponse) foundDueUser).getUser();
                        int userDueAmount = due.getAmount();
                        //woody,andy
                        //find sub user due list ex-woody->andy
                        ArrayList<String> allSubUSer = new ArrayList<>();
                        for (Dues d : dueUser.getDues()) {
                            allSubUSer.add(d.getLentBy());
                        }
                        for (String subUser : allSubUSer) {
                            if (allDueUser.contains(subUser)) {
                                lentUser = subUser;
                                dueAmount = due.getAmount();
                                due.setAmount(0);
                                repository.save(dueUser);
                            }
                        }
                        for (Dues d : dueUser.getDues()) {
                            if (d.getLentBy().equals(lentUser)) {
                                d.setAmount(d.getAmount() - dueAmount);
                                repository.save(dueUser);
                            }
                        }

                    }
                    for (Dues due : user.getDues()) {
                        if (due.getLentBy().equals(lentUser)) {
                            due.setAmount(due.getAmount() + dueAmount);
                            repository.save(user);
                        }
                    }

                    for (Dues due : user.getDues()) {
                        System.out.println(due.getLentBy() + " " + due.getAmount());
                    }
                } else {
                    System.out.println("FAILURE");
                }
            }

        } else if (response instanceof ClearDueResponse) {
            ClearDueResponse clearDueResponse = (ClearDueResponse) response;
            if (!repository.find(clearDueResponse.getWhoOwes())) {
                System.out.println("MEMBER_NOT_FOUND");
            } else if (!repository.find(clearDueResponse.getWhoLent())) {
                System.out.println("MEMBER_NOT_FOUND");
            } else {
                Response foundWoesUser = repository.getByUserName(clearDueResponse.getWhoOwes());
                User user = ((UserResponse) foundWoesUser).getUser();
                int totalDuesToLentByUser = 0;
                for (Dues due : user.getDues()) {
                    if (due.getLentBy().equals(clearDueResponse.getWhoLent())) {
                        totalDuesToLentByUser += due.getAmount();
                    }
                }
                if (totalDuesToLentByUser < clearDueResponse.getAmount()) {
                    System.out.println("INCORRECT_PAYMENT");
                } else {
                    totalDuesToLentByUser = totalDuesToLentByUser - clearDueResponse.getAmount();
                    for (Dues due : user.getDues()) {
                        if (due.getLentBy().equals(clearDueResponse.getWhoLent())) {
                            due.setAmount(totalDuesToLentByUser);
                            break;
                        }
                    }
                    repository.save(user);
                    System.out.println(totalDuesToLentByUser);
                }
            }

        } else if (response instanceof MoveOutResponse) {
            MoveOutResponse moveOutResponse = (MoveOutResponse) response;
            if (!repository.find(moveOutResponse.getMemberName())) {
                System.out.println("MEMBER_NOT_FOUND");
            } else {
                Response foundUser = repository.getByUserName(moveOutResponse.getMemberName());
                User user = ((UserResponse) foundUser).getUser();
                int totalDueleft = 0;
                for (Dues due : user.getDues()) {
                    totalDueleft += due.getAmount();
                }
                if (totalDueleft == 0) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAILURE");
                }
            }
        }
    }
}
